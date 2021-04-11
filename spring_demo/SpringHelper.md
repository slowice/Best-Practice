1.4.5   Autowiring Collaborators
自动装配分为byType和byName.byName是通过setName来判断的。
可以通过autowire-candidate属性设置为false，让某个bean无法自动装配。autowire-candidate只影响类型装配。